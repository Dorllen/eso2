var closeChoose = function() {
	var $body = $("body");
	$body.css('height', '');
	$body.removeClass('static');
	$("#choose").toggleClass("show");
}
var login = function(event) {
	event = event || window.event;
	alert("显示登陆窗体！")
	if (event.preventDefault) {
		event.preventDefault();
	}
	if (event.stopPropagation) {
		event.stopPropagation();
	}
	return false;
}
var register = function(event) {
	event = event || window.event;
	alert("显示注册窗体！");
	if (event.preventDefault) {
		event.preventDefault();
	}
	if (event.stopPropagation) {
		event.stopPropagation();
	}
	return false;
}

var confirmChoose = function() {
	// 
	var defaults_ = [];
	$(".c_default").find("span i.icon-check").each(function(i, v) {
		defaults_.push($(this).siblings('b').html());
	});
	var websites_ = [];
	$(".c_websites").find("span i.icon-check").each(function(i, v) {
		websites_.push($(this).siblings('b').html());
	})
	var design_ = [];
	$(".c_add").find("span i.icon-check").each(function(i, v) {
		design_.push($(this).siblings("b").html());
	});
	$("#from").val(websites_.join('|'));
	$("#design").val(design_.join('|'));
	// 关闭#choose面板
	var $body = $("body");
	$body.css('height', '');
	$body.removeClass('static');
	$("#choose").toggleClass("show");
}

$(function() {
	$(".c_default").on(
			"click",
			"span",
			function() {
				var v = $(this).children("b").html();
				if ($(this).children("i").hasClass('icon-check')) {
					$(this).children("i").removeClass('icon-check').addClass(
							'icon-check-empty');
					var $t = $(".c_websites span").find(
							"b:contains('" + v + "')");
					if ($t && $t.length > 0) {
						for ( var z in $t) {
							if (v === $t.eq(z).html()) {
								$t.eq(z).siblings("i")
										.removeClass('icon-check').addClass(
												'icon-check-empty');
							}
						}
					}
				} else {
					$(this).children("i").removeClass('icon-check-empty')
							.addClass('icon-check');
					var $t = $(".c_websites span").find(
							"b:contains('" + v + "')");
					if ($t && $t.length > 0) {
						for ( var z in $t) {
							if (v === $t.eq(z).html()) {
								$t.eq(z).siblings("i").removeClass(
										'icon-check-empty').addClass(
										'icon-check');
							}
						}
					}
				}
			})
	$(".c_websites").on(
			"click",
			"span",
			function() {
				var v = $(this).children("b").html();
				if ($(this).children("i").hasClass('icon-check')) {
					$(this).children("i").removeClass('icon-check').addClass(
							'icon-check-empty');
					var $t = $(".c_default span").find(
							"b:contains('" + v + "')");
					if ($t && $t.length > 0) {
						for ( var z in $t) {
							if (v === $t.eq(z).html()) {
								$t.eq(z).siblings("i")
										.removeClass('icon-check').addClass(
												'icon-check-empty');
							}
						}
					}
				} else {
					$(this).children("i").removeClass('icon-check-empty')
							.addClass('icon-check');
					var $t = $(".c_default span").find(
							"b:contains('" + v + "')");
					if ($t && $t.length > 0) {
						for ( var z in $t) {
							if (v === $t.eq(z).html()) {
								$t.eq(z).siblings("i").removeClass(
										'icon-check-empty').addClass(
										'icon-check');
							}
						}
					}
				}
			})
	$(".c_add").on(
			"click",
			"span",
			function() {
				if ($(this).children("i").hasClass('icon-check')) {
					$(this).children("i").removeClass('icon-check').addClass(
							'icon-check-empty');
				} else {
					$(this).children("i").removeClass('icon-check-empty')
							.addClass('icon-check');
				}
			})
	// 搜索框
	$("#siteName").on(
			"input",
			function() {
				if (this.value) {
					var v_ = this.value;
					$.ajax({
						url : "searchWebsite",
						type : "GET",
						data : {w:v_},
						success : function(data) {
							// 显示数据
							$("ul.c_search_items").empty();
							$.each(data, function(i, v) {
								$("ul.c_search_items").append(
										"<li>" + v['website'] + "</li>")
							})
							$("ul.c_search_items").show();
						},
						error : function(data) {
							alert("加载失败");
						}
					})
				} else {
					$("ul.c_search_items").hide();
				}
			})

	$("ul.c_search_items").on(
			"click",
			"li",
			function() {
				var v = $(this).html();
				if (v) {
					$(".c_add").children("p").remove(); // 移除默认p
					// 如果有值则不增加，没有则增加
					var $span = $(".c_add")
							.find("span b:contains('" + v + "')");
					if ($span && $span.length > 0) {
						for ( var z in $span) {
							if (v === $span.eq(0).html()) {
								$("ul.c_search_items").hide();
								return false;
							}
						}
					}
					$(".c_add").append(
							"<span><i class='icon-check'></i><b>" + v
									+ "</b></span>");
					$("ul.c_search_items").hide();
				}
			});

	$(".set_btn")
			.on(
					"click",
					function() {
						var height = $(window).height();
						var height_ = $(window).scrollTop();
						$("body").height(height);
						$("body").addClass('static')
						$("#choose").height(height);
						$("#choose").css("top", height_);
						$("#choose").toggleClass("show");
						// 初始化#choose面板
						var defaults = $("#defaults").val();
						// default值置入
						$(".c_default").children("span").remove();
						var arr = defaults.split("|");
						for ( var i in arr) {
							if (arr[i]) {
								$(".c_default").append(
										"<span><i class='icon-check-empty'></i><b>"
												+ arr[i] + "</b></span>");
							}
						}
						$(".c_websites").children("span").remove();
						var defaultwebsites = $("#defaultWebsites").val();
						var arr1 = defaultwebsites.split("|");
						for ( var i in arr1) {
							if (arr1[i]) {
								$(".c_websites").append(
										"<span><i class='icon-check-empty'></i><b>"
												+ arr1[i] + "</b></span>")
							}
						}
						var from_ = $("#from").val();
						if (from_) {
							var arr2 = from_.split("|");
							for ( var i in arr2) {
								if (arr2[i]) {
									// 将c_websites的勾选
									var $t = $(".c_websites span b:contains('"
											+ arr2[i] + "')");
									if ($t && $t.length > 0) {
										for ( var z in $t) {
											if (arr2[i] === $t.eq(z).html()) {
												$t
														.eq(z)
														.siblings('i')
														.removeClass(
																'icon-check-empty')
														.addClass('icon-check');
											}
										}

									}
									// 将c_defaults
									$t = $(".c_default span b:contains('"
											+ arr2[i] + "')");
									if ($t && $t.length > 0) {
										for ( var z in $t) {
											if (arr2[i] === $t.eq(z).html()) {
												$t
														.eq(z)
														.siblings('i')
														.removeClass(
																'icon-check-empty')
														.addClass('icon-check');
											}
										}
									}

								}
							}
						}
						var design = $("#design").val();
						$(".c_add").children("span").remove();
						$(".c_add").children("p").remove();
						$("#siteName").val('');
						$("ul.c_search_items").hide();
						if (design) {
							var arr3 = design.split('|');
							for ( var i in arr3) {
								$(".c_add").append(
										"<span><i class='icon-check'></i><b>"
												+ arr3[i] + "</b></span>");
							}
						} else {
							// 显示
							$(".c_add")
									.append(
											"<p>请在下输入进行模糊搜索<i class='icon-arrow-down'></i></p>");
						}
					})

	$("div.type span").on(
			"click",
			function() {
				var $i = $(this).children("i");
				var $s = $(this).siblings("span").first();
				var $si = $s.children("i");
				if ($i.hasClass('icon-check-empty')) {
					if ($si.hasClass('icon-check')) {
						if ($s.is("#sSources")) {
							// 
							if (confirm("在线获取数据，耗时较长，是否继续？")) {
								$i.removeClass('icon-check-empty').addClass(
										"icon-check");
								$si.removeClass('icon-check').addClass(
										'icon-check-empty');
								console.log("获取数据中...")
								alert("加载成功！");
								// 使用pushState+ajax获取
								// 更新搜索结果
								// 更新所有信息
							}
						} else {
							$i.removeClass('icon-check-empty').addClass(
									"icon-check");
							$si.removeClass('icon-check').addClass(
									'icon-check-empty');
						}
					}
				}
			})
	$("div.tagsChoose").on("click", "span", function() {
		// 遮罩
		$(this).toggleClass('remove');
		// not .remove的span标签
		var $arr = $(this).parent("div").children("span:not(.remove)");
		var arr = [];
		$arr.each(function(i, v) {
			if (v) {
				var q = $(v).children("b").html();
				if (q) {
					arr.push(q);
				}
			}
		})
		// 异步请求
		console.log("loading tagsChoose:");
		console.log(arr);
		return false;
	})

	$("div.from").on("click", "span", function() {
		// 遮罩
		// 异步请求
		$(this).toggleClass('remove');
		var $arr = $(this).parent("div").children("span:not(.remove)");
		var arr = [];
		$arr.each(function(i, v) {
			if (v) {
				var q = $(v).children("b").html();
				if (q) {
					arr.push(q);
				}
			}
		})
		console.log("loading from:");
		console.log(arr);
		return false;
	})

	$("div.mSort").on(
			"click",
			"span",
			function() {
				if ($(this).children('i').hasClass('icon-check-empty')) {
					// 遮罩

					// 清除其他勾选
					$(this).siblings("span").children("i.icon-check")
							.removeClass('icon-check').addClass(
									'icon-check-empty');
					$(this).children('i').removeClass('icon-check-empty')
							.addClass('icon-check');
					// 异步请求
				}
				return false;
			})
	$("div.sSort").on("click", "span", function() {
		if (!$(this).hasClass('active')) {
			// 遮罩
			$(this).siblings("span").removeClass('active');
			$(this).addClass('active');
			// 异步请求

		}
		return false;
	})
	$("div.cBar").on("click", function() {
		var $i = $(this).find("i");
		if ($i.hasClass('icon-chevron-up')) {
			$i.removeClass('icon-chevron-up').addClass('icon-chevron-down');
		} else {
			$i.removeClass('icon-chevron-down').addClass('icon-chevron-up');
		}
		$("div.origin,div.sort").toggleClass('show');
	});
	$(".content").on(
			"click",
			".service_2 span.sc",
			function(event) {
				var $item = $(this).parents("div.item")
				var $i = $(this).children("i");
				var dataId = $item.attr("data-id");
				// 從cookie中鉴别是否登陆了
				if (true) {
					// 登陆的操作
					if ($i.hasClass('icon-star-empty')) {
						$i.removeClass('icon-star-empty').addClass(
								'icon-star colorRed2');
					} else {
						$i.removeClass('icon-star colorRed2').addClass(
								'icon-star-empty');
					}
				} else {
					// 未登录的操作
					alert("亲，请先登录哦！");
				}
				return false;
			})
	$(".content").on("click", ".service_2 span.tj", function(event) {
		var $item = $(this).parents("div.item")
		var $s = $(this).siblings("span.badTj");
		var $i = $(this).children("i");
		var dataId = $item.attr("data-id");
		// 從cookie中鉴别是否登陆了
		if (true) {
			// 登陆的操作
			// 前端识别是否点了“差评”
			if ($i.hasClass('colorRed2')) {
				alert("您已经评价了");
			} else {
				if ($s.find("i").first().hasClass('colorRed2')) {
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
	$(".content").on("click", ".service_2 span.badTj", function(event) {
		var $item = $(this).parents("div.item")
		var $s = $(this).siblings("span.tj");
		var $i = $(this).children("i");
		var dataId = $item.attr("data-id");
		// 從cookie中鉴别是否登陆了
		if (true) {
			// 登陆的操作
			if ($i.hasClass('colorRed2')) {
				alert("您已经评价了");
			} else {
				if ($s.find("i").first().hasClass('colorRed2')) {
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

	$("li#tools").on("click", function() {
		$(this).toggleClass('show');
	})

})