<template>
    <div>
        <modal name="modal-classCreate" height="700px" width="700px" @before-open="beforeOpen">
        <form id="file-upload-form" class="uploader" style="margin-top:40px;" enctype="multipart/form-data">
          <div id="classcreatemodal">
            <div class="classcreatemodaldiv1">
              <h2>새 교실 추가</h2>
            </div>
            <div class="classcreatemodaldiv2">
              <div v-if="!classimg">
                <div class="noimg">
                  <label for="photo" id="file-drag-not_img" ref="dropbox">
                    <h4>교실 이미지 등록</h4>
                    <input type="file" id="photo" accept="image/*" @change="onFileChange"/>
                  </label>
                </div>
              </div>
              <div v-else>
                <div class="haveimg">
                  <label class="file-upload" for="photo" id="file-drag" ref="dropbox">
                    <img :src="classimg" class="img_upload"/>
                  </label>
                  <input type="file" id="photo" accept="image/*" @change="onFileChange"/>
                </div>
              </div>
              <div class="textclasscreate">
                <div class="floatleft">
                  <label for="classname" class="floatleft">교실 이름 : </label>
                  <b-form-input v-model="classname" id="classname" type="text" placeholder="교실 이름을 입력해주세요." maxlength="8"/>
                </div>
                <div>
                  <label for="userlimit" class="floatleft">최대 인원 : </label>
                  <b-form-input v-model="userlimit" id="userlimit" type="text" maxlength="2" placeholder="10"/>
                </div>
                <div>
                  <label for="teenager" class="floatleft">수강 연령 : </label>
                  <b-form-select v-model="targetage" id="teenager" :options="age"/>
                </div>
              </div>
            </div>
            <div class="classcreatemodaldiv3">
              <div>
                <label for="classdesc" class="classdescl floatleft">교실 소개 : </label>
              </div>
              <div>
                <b-form-textarea v-model="classdesc" id="classdesc" maxlength="50" placeholder="교실에 대한 간략한 설명을 50자 내로 적어주세요"/>
              </div>
            </div>
            <!-- <br>
            <label for="opentype">공개 여부 : </label>
            <input v-model="classdesc" id="opentype" type="textarea" placeholder="10"/> -->
            <div class="classcreatemodaldiv4">
              <div>
                <label for="starttime" class="floatleft">수업 기간 : </label>
              </div>
              <div>
                <input v-model="startdate" id="starttime" type="date"/>
                ~
                <label for="endtime"></label>
                <input v-model="enddate" id="endtime" type="date"/>
              </div>
            </div>
            <div class="classcreatemodaldiv5">
              <b-button class="btn" @click="createclass" v-if="!create_class_flag">개설</b-button>
            </div>
          </div>
        </form>
      </modal>
    </div>
</template>