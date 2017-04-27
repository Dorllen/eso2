$(function() {
	$("div#main a").attr("href","#");
	$(window).on("scroll", function() {
		var height = $(window).scrollTop();
		// 用于从cookie中鉴别是否是点击了稍后再说，或者已经登陆了。
		var t = $.cookie("isLogin");
		var c = $.cookie("setTime"); // setTime timestatmp
		if(!t) {
			var d = new Date().getTime();
			if(!c || (c && c < d)) {
				if(height > 300) {
					$("#bottomInfo").slideDown("slow");
				}
			}
		}
	})
	$("span.answerLater").on("click", function() {
		var d = new Date();
		d.setDate(d.getDate() + 1);
		//			$.cookie("setTime",d.getTime());
		$("#bottomInfo").slideUp("slow");
		return false;
	})
	$("span.joinUs").on("click", function() {
		alert("感谢加入我们！");
	})
	$("span.sc").on("click", function() {
		var $i = $(this).children("i");
		var dataId = $(this).parent("div.topDiv3").attr("data-id");
		// 從cookie中鉴别是否登陆了
		if(true) {
			// 登陆的操作
			if($i.hasClass('icon-star-empty')) {
				$i.removeClass('icon-star-empty').addClass('icon-star colorRed2');
			} else {
				$i.removeClass('icon-star colorRed2').addClass('icon-star-empty');
			}
		} else {
			// 未登录的操作
			alert("亲，请先登录哦！");
		}
		return false;
	})
	$("span.tj").on("click", function() {
		var $s = $(this).siblings("span.badTj");
		var $i = $(this).children("i");
		var dataId = $(this).parent("div.topDiv3").attr("data-id");
		// 從cookie中鉴别是否登陆了
		if(true) {
			// 登陆的操作

			// 前端识别是否点了“差评”
			if($i.hasClass('colorRed2')) {
				alert("您已经评价了");
			} else {
				if($s.find("i").first().hasClass('colorRed2')) {
					// 取消"赞"的样式	
					$s.find("i").first().removeClass('colorRed2');
				}
				$i.addClass("colorRed2");
			}
		} else {
			// 未登录的操作
			alert("亲，请先登录哦！");
		}
		return false;
	})
	$("span.badTj").on("click", function() {
		var $s = $(this).siblings("span.tj");
		var $i = $(this).children("i");
		var dataId = $(this).parent("div.topDiv3").attr("data-id");
		// 從cookie中鉴别是否登陆了
		if(true) {
			// 登陆的操作

			if($i.hasClass('colorRed2')) {
				alert("您已经评价了");
			} else {
				if($s.find("i").first().hasClass('colorRed2')) {
					// 取消"赞"的样式	
					$s.find("i").first().removeClass('colorRed2');
				}
				$i.addClass("colorRed2");
			}
		} else {
			// 未登录的操作
			alert("亲，请先登录哦！");
		}
		return false;
	})
})