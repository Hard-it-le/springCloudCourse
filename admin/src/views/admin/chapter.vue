<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-plus"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="getList(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <!--新增章节列表模态框 -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="exampleModalLabel">新增章节</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="course-name" class="control-label">课程名称</label>
                <input v-model="course.name" type="text" class="form-control" id="course-name" placeholder="课程名称">
              </div>
              <div class="form-group">
                <label for="course-id" class="control-label">课程ID</label>
                <input type="number" v-model="course.courseId" class="form-control" id="course-id" placeholder="课程ID">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" @click="saveCourse()">确认</button>
          </div>
        </div>
      </div>
    </div>

    <!--编辑章节列表模态框 -->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="updateModalLabel">修改章节</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="course-updateName" class="control-label">课程名称</label>
                <input v-model="course.name" type="text" class="form-control" id="course-updateName" placeholder="课程名称">
              </div>
              <div class="form-group">
                <label for="course-updateId" class="control-label">课程ID</label>
                <input type="number" v-model="course.courseId" class="form-control" id="course-updateId"
                       placeholder="课程ID">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" @click="updateCourse()">确认</button>
          </div>
        </div>
      </div>
    </div>


    <!-- 分页组件的使用 -->
    <pagination ref="pagination" v-bind:list="getList" v-bind:itemCount="5"></pagination>
    <!-- 列表的展示-->
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>课程ID</th>
        <th>操作</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{ chapter.id }}</td>
        <td>{{ chapter.name }}</td>
        <td>{{ chapter.courseId }}</td>
        <td>
          <div class="btn-group">
            <button class="btn btn-xs btn-info" @click="update(chapter)">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button class="btn btn-xs btn-danger" @click="del(chapter.id)">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>

      </tbody>
    </table>
  </div>
</template>


<script>
import Pagination from "@/components/pagination";

export default {
  name: "chapter",
  components: {Pagination},
  data: function () {
    return {
      chapters: {},
      course: {},

    }
  },
  mounted() {
    let _this = this;
    //设置分页条数为5条
    _this.$refs.pagination.size = 5;
    _this.getList(1);
  },
  methods: {

    //打开编辑模态框
    update(chapter) {
      $("#updateModal").modal('show');
      let _this = this;
      _this.course = $.extend({}, chapter);
    },
    //修改章节并关闭模态框
    updateCourse() {
      let _this = this;
      Loading.show();
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/edit", {
        id: _this.course.id,
        courseId: _this.course.courseId,
        name: _this.course.name
      }).then(updateRes => {
        Loading.hide();
        if (null != updateRes && null != updateRes.data && updateRes.data.success) {
          $("#updateModal").modal('hide');
          _this.getList(1);
        } else {
          alert("参数不能为空");
        }
      }).catch(updateEx => {
        console.info(updateEx);
      });
    },


    //删除章节目录
    del(delId) {
      let _this = this;
      var id = delId;
      Loading.show();
      _this.$ajax.delete("http://127.0.0.1:9000/business/admin/chapter/delete/" + id).then(deleteRes => {
        Loading.hide();
        if (null != deleteRes && null != deleteRes.data && deleteRes.data.success) {
          _this.getList(1);
        }else {
          alert("删除失败请稍后再试");
        }
      }).catch(deleteEx => {
        console.info(deleteEx);
      });
    },


    //打开模态框
    add() {
      $("#addModal").modal('show');
    },
    //添加章节目录并关闭模态框
    saveCourse() {
      let _this = this;
      Loading.show();
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/save", {
        courseId: _this.course.courseId,
        name: _this.course.name
      }).then(saveRes => {
        Loading.hide();
        if (null != saveRes && null != saveRes.data && saveRes.data.success) {
          $("#addModal").modal('hide');
          _this.getList(1);
        } else {
          alert(saveRes.data.message);
        }
      }).catch(saveEx => {
        console.info(saveEx);
      });
    },


    //查询大章列表
    getList(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/list", {
        page: page,
        size: _this.$refs.pagination.size,
      }).then((listRes) => {
        Loading.hide();
        _this.chapters = listRes.data.content.list;
        _this.$refs.pagination.render(page, listRes.data.content.total);
      }).catch(ex => {
        alert("网络异常请重新登录");
      });
    },
  },
}
</script>