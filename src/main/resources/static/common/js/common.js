/*
//높이 맞추기
const obj1 = document.getElementById('content_wrap');
const obj2 = document.getElementById('left_menu');

const obj1_height = obj1.clientHeight;
const obj2_height = obj2.clientHeight;
console.log(obj2_height);

if (obj1_height > obj2_height) {
	obj2.style.height = obj1_height + 'px';
} else {
	obj1.style.height = obj2_height + 'px';
}
*/

function tableAllChk(e){

	if($(e).prop('checked')){
		$(e).parents('table').find('tbody td input[type=checkbox]').prop('checked',true);
	}else{
		$(e).parents('table').find('tbody td input[type=checkbox]').prop('checked',false);
	}
}
