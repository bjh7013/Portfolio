<template>

<div class="icon-wrapper">
	<div id="loading-icon" class="loader">
		<div id="inner-loading-icon" class="loader"></div>
    </div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>

/* Center Align Icon Wrapper */
.icon-wrapper {
/* position: absolute; */

  /* transform: translate(-50%, -50%); */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Loader Icon Styles */ 
.loader {
	border-radius: 50%;
}

#loading-icon {
	display: table-cell;
	vertical-align: middle;
	width: 40px; 
	height: 40px;
	border: 8px solid rgba(0, 0, 0 , 0);
	border-top: 8px solid rgb(150, 150, 255);
	border-bottom: 8px solid rgb(150, 150, 255);
	animation: spin 4s linear infinite, color 2s linear infinite;
	/* ^ styles two animations and sets their animation-name, animation-duration, animation-timing-function and animation-iteration-count - in that order*/
}

#inner-loading-icon {
	margin: auto;
	width: 20px;
	height: 20px;
	border: 5px solid rgba(0, 0, 0, 0);
	border-top: 5px solid rgb(150, 150, 255);
	border-bottom: 5px solid rgb(150, 150, 255);
	animation: spin 2s linear infinite, color 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

@keyframes color {
	0% { 
		border-top-color: rgb(255, 150, 150); 
		border-bottom-color: rgb(255, 150, 150); 
	}
	33% { 
		border-top-color: rgb(150, 255, 150);
		border-bottom-color: rgb(150, 255, 150); 
	}
	66% { 
		border-top-color: rgb(150, 150, 255); 
		border-bottom-color: rgb(150, 150, 255);
	}
	100% { 
		border-top-color: rgb(255, 150, 150); 
		border-bottom-color: rgb(255, 150, 150); 
	}
}
</style>

