<template>
    <div id="editor">
        <div class="editor_header">
            <span>제목 : </span> <input type="text" v-model="title">
            <select id="articleType" v-model="articleType">
                <option disabled hidden value="default">==게시글 유형==</option>
                <option value="N">일반</option>
                <option value="A">알고리즘</option>
            </select>
            <select id="boardList" v-model="boardNo">
                <option disabled hidden value="default">==게시판 선택==</option>
            </select>
            <select id="openScope" v-model="openScope">
                <option value="public">전체 공개</option>
                <option value="protected">친구 공개</option>
                <option value="private">비공개</option>
            </select>
            <label><input type="checkbox" value="sharable" v-model="sharable">공유 허용</label>
            <label><input type="checkbox" value="reviewRequest" v-model="reviewRequest">리뷰 요청</label>
        </div>

        <ckeditor v-model="editorData" :config="editorConfig"></ckeditor>
        <button @click="writeArticle">작성</button>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            editorData: '<p>Content of the editor.</p>',
            editorConfig: {
                // The configuration of the editor.
                extraPlugins: 'lineutils, widget, codesnippet, prism, image2,youtube',
                codeSnippet_theme: 'monokai_sublime',
                filebrowserImageBrowseUrl: 'http://localhost:9999/ckfinder/static/ckfinder.html',
                filebrowserImageUploadUrl: 'http://localhost:9999/ckfinder/connector?command=FileUpload&lang=ko&langCode=ko&type=Images&currentFolder=%2F&hash=64b554bde9ee58bd&responseType=json',
                filebrowserUploadUrl : 'http://localhost:9999/ckfinder/connector?command=FileUpload&lang=ko&langCode=ko&type=Images&currentFolder=%2F&hash=64b554bde9ee58bd&responseType=json'
            },
            title: '',
            articleType: 'default',
            boardNo: '',
            openScope: 'public',
            sharable: '',
            reviewRequest: '',
            articleTagCat:{
            },
        };
    },
    methods:{
        writeArticle(){
            axios.post("/article/article", {
                boardNo: this.boardNo,
                title: this.title,
                content: this.editorData,
                scope: this.openScope,
                articleType: this.articleType,
                sharable: this.sharable,
                reviewRequest: this.reviewRequest
            })
            .then(() => {
                alert("작성완료");
            })
            .catch(() =>{
                alert("게시글 작성 실패");
            })
        }
    }
}
</script>
<style scoped>

</style>