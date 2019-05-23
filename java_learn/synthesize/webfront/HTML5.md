## HTML5
页面嵌入
```
<iframe src="URL"></iframe>
```
**Window 对象**
表示一个浏览器窗口或一个框架
Window 对象表示浏览器中打开的窗口。

如果文档包含框架（frame 或 iframe 标签），浏览器会为 HTML 文档创建一个 window 对象，并为每个框架创建一个额外的 window 对象。
```
window.self//自身窗口
window.top//顶层窗口
window.parent//返回父窗口
```
```
frame[i]		//当前窗口的框架
self.frame[i]	//当前窗口的框架
w.frame[i]	//窗口 w 的框架
```
要从顶层窗口含有的任何一个框架中引用它，可以使用如下语法：
```
top		//当前框架的顶层窗口
self.top		//当前框架的顶层窗口
f.top		//框架 f 的顶层窗口
```