var section = document.querySelector("section");
var choose = document.querySelector("#choose");
var btn = document.querySelector(".set_btn");
var person = document.querySelector(".person");
var form = document.querySelector("form");
var query = form.querySelector("#query");
var f = form.querySelector("#from");
var type = form.querySelector("#type");
var design = form.querySelector("#design"); // segmentfault|github
var defaults = form.querySelector("#defaults");
var defaultWebsites = form.querySelector("#defaultWebsites");
var panel_default = choose.querySelector(".c_default");
var panel_websites = choose.querySelector(".c_websites");
var panel_design = choose.querySelector(".c_add");
var siteName = document.querySelector("#siteName"); // input
var c_search_items = document.querySelector(".c_search_items"); // 选择来源的ul标签
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

var closeChoose = function() {
	toggleClass(choose, 'show');
}
var getCheckedSpan = function(obj) {
	var arr = [];
	if (!obj)
		return arr;
	var obj_spans = obj.querySelectorAll("span");
	for (var span_ = 0; span_ < obj_spans.length; span_++) {
		var o = obj_spans[span_];
		var checked_ = o.querySelector("i.icon-check");
		if (checked_) {
			arr.push(checked_.nextSibling.innerText);
		}
	}
	return arr;
}
var confirmChoose = function() {
	// 找出选择的站点
	// 获得选择的item
	var arr = getCheckedSpan(panel_websites);
	var arr2 = getCheckedSpan(panel_design);
	f.value = arr.join("|");
	design.value = arr2.join("|");
	// 异步添加到
	toggleClass(choose, 'show');
}
var toggleClass = function(obj, claName) {
	var claz = obj.className;
	if (claz && claz.length != 0) {
		var i = claz.indexOf(claName);
		if (i != -1) {
			obj.className = deleteClassItem(claz, claName);
		} else {
			obj.className = obj.className + " " + claName;
		}
	} else {
		obj.className = claName;
	}
}
var toggleClass2 = function(obj, claz1, claz2) {
	var claz = obj.className;
	if (claz && claz.length != 0) {
		if (claz2.indexOf(claz1) != -1) {
			var t = claz2;
			claz2 = claz1;
			claz1 = t;
		}
		var i = claz.indexOf(claz1);
		var i2 = claz.indexOf(claz2);
		if (i != -1) {
			// claz1加入
			obj.className = deleteClassItem(claz, claz1);
			obj.className = obj.className + " " + claz2;
			return claz2;
		} else if (i2 != -1) {
			// claz2加入
			obj.className = deleteClassItem(claz, claz2);
			obj.className = obj.className + " " + claz1;
			return claz1;
		}
	} else {
		obj.className = claz1;
		return claz1;
	}
}
var deleteClassItem = function(str, claName) {
	var i = str.indexOf(claName);
	if (str && str.length > 0 && claName && claName.length > 0 && i != -1) {
		return str.substring(0, i).trim()
				+ str.substring(i + claName.length, str.length);
	} else {
		return str;
	}
}
var removeLiActive = function(obj) {
	// 移除父节点下的所有li的.active的active
	var sectionLi = obj.querySelectorAll("li.active");
	for ( var t_ in sectionLi) {
		var li = sectionLi[t_];
		if (li.className) {
			li.className = deleteClassItem(li.className, 'active');
		}
	}
}
var changeForm = function(index) {
//	var arr = JSON
//			.parse('[{ "p": "查找关键字:python/java/ElasticSearch", "f": "segmentfault|github", "t": "answer","defaults":"segmentfault|github","defaultWebsites":"segmentfault|github|cnblog|python3" }, { "p": "暂不提供服务", "f": "","t": "blog","defaults":"","defaultWebsites":"" }, { "p": "暂不提供服务", "f": "", "t": "software","defaults":"","defaultWebsites":"" }, { "p": "暂不提供服务", "f": "", "t": "others","defaults":"","defaultWebsites":"" }]');
	var arr = JSON.parse(global_value);
	var obj = arr[index];
	query.placeholder = obj["p"]
	type.value = obj["t"];
	f.value = obj["f"];
	defaultWebsites.value = obj["defaultWebsites"];
	defaults.value = obj["defaults"];
	design.value = "";
}

var removeChildren = function(obj) {
	var nodes = obj.children;
	for (var b = 0; nodes.length > 0;) {
		obj.removeChild(nodes[b]);
	}
}
var addChilrenSpan = function(obj, arr, checkedArr) {
	for (var i = 0; i < arr.length; i++) {
		if (!arr[i]) {
			continue;
		}
		var span = document.createElement("span");
		if (checkedArr) {
			if (hasItemInArr(checkedArr, arr[i])) {
				span.innerHTML = "<i class='icon-check'></i><b>" + arr[i]
						+ "</b>"
				obj.appendChild(span);
			} else {
				span.innerHTML = "<i class='icon-check-empty'></i><b>" + arr[i]
						+ "</b>"
				obj.appendChild(span);
			}
		} else {
			span.innerHTML = "<i class='icon-check'></i><b>" + arr[i] + "</b>"
			obj.appendChild(span);
		}
	}
}
var addChilrenSpanEventListener1 = function() {
	// choose.querySelector(".c_websites")
	var default_span = panel_default.querySelectorAll("span");
	for ( var i in default_span) {
		default_span[i].index = i;
		default_span[i].onclick = function() {
			var str = toggleClass2(this.getElementsByTagName("i")[0],
					'icon-check', 'icon-check-empty');
			if (str) {
				// 级联到站点中
				var v = this.innerText;
				var llist = panel_websites.querySelectorAll("span");
				for ( var lz in llist) {
					var lzv = llist[lz].innerText;
					if (lzv === v) {
						llist[lz].querySelector("i").className = str; // i标签里的class只有一个！
					}
				}
			}
		}
	}
}
var addChildrenSpanEventListener2 = function() {
	var websites_span = panel_websites.querySelectorAll("span");
	for ( var i in websites_span) {
		websites_span[i].index = i;
		websites_span[i].onclick = function() {
			var str = toggleClass2(this.getElementsByTagName("i")[0],
					'icon-check', 'icon-check-empty');
			if (str) {
				// 级联到站点中
				var v = this.innerText;
				var llist = panel_default.querySelectorAll("span");
				for ( var lz in llist) {
					var lzv = llist[lz].innerText;
					if (lzv === v) {
						llist[lz].querySelector("i").className = str; // i标签里的class只有一个！
					}
				}
			}
		}
	}
}
var addChildrenSpanEventListener3 = function() {
	var design_span = panel_design.querySelectorAll("span");
	for ( var i in design_span) {
		design_span[i].index = i;
		design_span[i].onclick = function() {
			var str = toggleClass2(this.getElementsByTagName("i")[0],
					'icon-check', 'icon-check-empty');
		}
	}
}
var getSameArrValue = function(arr1, arr2) {
	var arr = [];
	for ( var a in arr1) {
		for ( var z in arr2) {
			if (arr1[a] === arr2[z]) {
				arr.push(arr1[a]);
			}
		}
	}
	return arr;
}
var hasItemInArr = function(arr, item) {
	for ( var a in arr) {
		if (item && item == arr[a]) {
			return true;
		}
	}
	return false;
}
var addDesignUlEventListener = function() {
	var list = c_search_items.querySelectorAll("li");
	for ( var llist in list) {
		list[llist].onclick = function() {
			// 事件内容
			var v = this.innerText;
			// 验证是否不存在v的值，不存在则添加。
			if (hasNotBtextInUl(panel_design.querySelectorAll("span"), v)) { // 没有放原型中！
				// 检测是否存在指示,存在则清除
				panel_design.removeChild(panel_design.querySelector("p"));
				var span = document.createElement("span");
				span.innerHTML = "<i class='icon-check'></i><b>" + v + "</b>";
				panel_design.appendChild(span);
				addChildrenSpanEventListener3();
			} else {
				// 如果是icon-check-empty则变为icon-check
				var spans = panel_design.querySelectorAll("span");
				for (var span = 0; span < spans.length; span++) {
					var span_ = spans[span];
					if (v === span_.querySelector("b").innerText) {
						// 找到了
						var i_ = span_.querySelector("i.icon-check-empty");
						if (i_) {
							i_.className = deleteClassItem(i_.className,
									'icon-check-empty');
							i_.className = i_.className + " icon-check";
						}

					}
				}
				// toggleClass2("",'icon-check', 'icon-check-empty');
			}
			// siteName 清除input内容
			siteName.value = "";
			// 隐藏ul
			c_search_items.style.display = "none";
		}
	}
}
var hasNotBtextInUl = function(spans, text) {
	for (var span = 0; span < spans.length; span++) {
		var span_ = spans[span];
		if (text === span_.querySelector("b").innerText) {
			return false;
		}
	}
	return true;
}
window.onload = function(event) {
	section.style.height = window.innerHeight - 123 + "px";
	btn.onclick = function(event) {
		// 初始化页面
		// 初始化面板
		removeChildren(panel_default);
		removeChildren(panel_websites);
		removeChildren(panel_design);
		if (defaults && defaults.value) {
			var str = defaults.value.split("|");
			var fvalue = f.value.split("|");
			// str做处理
			// 找出str在fvalue的str的arr数组
			var temp_value = getSameArrValue(str, fvalue);
			addChilrenSpan(panel_default, str, temp_value);
			addChilrenSpanEventListener1();
		}
		if (defaultWebsites && defaultWebsites.value) {
			var d = defaultWebsites.value.split("|");
			var str = f.value.split("|");
			addChilrenSpan(panel_websites, d, str);
			addChildrenSpanEventListener2();
		}
		var str = design.value.split("|");
		if (design.value) {
			addChilrenSpan(panel_design, str);
			addChildrenSpanEventListener3();
		} else {
			// 告知在下面选择 <p>请在下输入进行模糊搜索<i class=" icon-arrow-down"></i></p>
			var p = document.createElement("p");
			p.innerHTML = '请在下输入进行模糊搜索<i class=" icon-arrow-down"></i>';
			panel_design.appendChild(p);
		}
		// 隐藏ul.c_search_items,清空input里的值
		siteName.value = "";
		c_search_items.style.display = 'none';
		toggleClass(choose, 'show');
	}
	var person_info = document.querySelector("ul.person_info");
	if (person_info) {
		person.onmouseover = function() {
			person_info.style.display = "block";
		}
		person.onmouseout = function() {
			person_info.style.display = "none";
		}
	}
	form.onsubmit = function() {
		var q = this.q.value;
		var t = this.t.value;
		var f = this.f.value;
		if (!q) {
			alert("请输入查询内容！");
		} else if (!t) {
			alert("请选择查询类型");
		} else if (!f) {
			alert("请选择搜索来源");
		} else {
			return true;
		}
		return false;
	}
/*	var liTools = document.querySelector("li#tools");
	liTools.onclick = function() {
		toggleClass(liTools, 'show')
	}*/
	var sectionLi = section.querySelectorAll("div ul li");
	for ( var i in sectionLi) {
		sectionLi[i].index = i;
		sectionLi[i].onclick = function() {
			if (this.className && this.className.contains("active")) {
				return false;
			} else {
				// 移除其他li.active
				removeLiActive(this.parentNode);
				// 变动搜索类型
				changeForm(this.index);
				if (this.className && this.className.length > 0) {
					this.className = this.className + " " + "active";
				} else {
					this.className = "active";
				}
			}
		}
	}
	// choose面板，输入框
	siteName.oninput = function() { // IE9 不兼容
		// 联网进行索引
		if (siteName.value) {
			var pro = new Promise(function(resolved, rejected) {
				// 联网请求数据
				getAysnc("siteName.json", function(data) {
					resolved(data);
				});
			});
			pro.then(function(data) {
				// 处理数据
				if (data) {
					removeChildren(c_search_items); // 清除内容
					c_search_items.style.display = "block";
					// 放值入siteName
					data = JSON.parse(data);
					for ( var item in data) {

						var li = document.createElement("li");
						li.innerText = data[item]["website"];
						c_search_items.appendChild(li);
					}
					addDesignUlEventListener();
				} else {
					removeChildren(c_search_items); // 清除内容
					c_search_items.style.display = "none";
				}
			}, function(data) {
				console.log("失败加载...");
			});
		} else {
			removeChildren(c_search_items); // 清除内容
			c_search_items.style.display = "none";
		}
	}

}