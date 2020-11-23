<template>
  <div>
    <v-footer class="font-weight-medium footer-background" style="background-color:#3b8686;">
      <v-col class="text-center" cols="12">
        <ul class="footer-links">
          <li style="color:white">도담도담 <a href="/dodam">이용안내</a></li>
          
        </ul>
        <p class="copyright-text">Copyright &copy; 2020 All Rights Reserved by 
        <router-link to="/bungle" style="color:white;">Bungle</router-link></p>
      </v-col>
    </v-footer>
  </div>
</template>

<script>
export default {
  name: 'footbar',
}
</script>

<style scoped>
.footer-background {
  background-color:#3b8686;
}
.app-footer h6
{
  font-size:16px;
  text-transform:uppercase;
  margin-top:0px;
  letter-spacing:2px
}
.app-footer a
{
  color: white;
}
.app-footer a:hover
{
  color:#444444;
  text-decoration:none;
}
.footer-links
{
  padding-left:0;
  list-style:none
}
.footer-links li
{
  display:inline;
}
.footer-links a
{
  color:white;
}
.footer-links a:active,.footer-links a:focus,.footer-links a:hover
{
  color:#444444;
  text-decoration:none;
}
.footer-links.inline li
{
  display:inline-block
}

.copyright-text
{
  margin:0
}
@media (max-width:991px)
{
  .app-footer [class^=col-]
  {
    margin-bottom:30px
  }
}
@media (max-width:767px)
{
  .app-footer
  {
    padding-bottom:0
  }
}
</style>