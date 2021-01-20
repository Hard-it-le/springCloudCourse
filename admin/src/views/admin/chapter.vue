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
          <div class="hidden-sm hidden-xs btn-group">
            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-warning">
              <i class="ace-icon fa fa-flag bigger-120"></i>
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                      data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                  class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                  </a>
                </li>
              </ul>
            </div>
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
    _this.$refs.pagination.size = 5;
    _this.getList(1);
  },
  methods: {
    //打开模态框
    add() {
      $("#addModal").modal('show');
    },
    //添加章节目录并关闭模态框
    saveCourse() {
      let _this = this;
      console.info(_this.course);
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/save", {
        courseId: _this.course.courseId,
        name:_this.course.name
      }).then(saveRes => {
        console.info(saveRes);
        $("#addModal").modal('hide');
        _this.getList(1);
      }).catch(saveEx => {
        console.info(saveEx);
      });
    },


    //查询大章列表
    getList(page) {
      let _this = this;
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/list", {
        page: page,
        size: _this.$refs.pagination.size,
      }).then((listRes) => {
        _this.chapters = listRes.data.content.list;
        _this.$refs.pagination.render(page, listRes.data.content.total);
      }).catch(ex => {
      });
    },
  },
}
</script>