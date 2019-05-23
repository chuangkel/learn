el

类型：string | Element

限制：只在由 new 创建的实例中遵守。

详细：

为实例提供挂载元素。值可以是 CSS 选择符，或实际 HTML 元素，或返回 HTML 元素的函数。注意元素只用作挂载点。如果提供了模板则元素被替换，除非 replace 为 false。元素可以用 vm.$el 访问。

用在 Vue.extend 中必须是函数值，这样所有实例不会共享元素。

如果在初始化时指定了这个选项，实例将立即进入编译过程。否则，需要调用 vm.$mount()，手动开始编译。

template

类型： String

详细：

实例模板。模板默认替换挂载元素。如果 replace 选项为 false，模板将插入挂载元素内。两种情况下，挂载元素的内容都将被忽略，除非模板有内容分发 slot。

如果值以 # 开始，则它用作选项符，将使用匹配元素的 innerHTML 作为模板。常用的技巧是用 <script type="x-template"> 包含模板。

注意在一些情况下，例如如模板包含多个顶级元素，或只包含普通文本，实例将变成一个片断实例，管理多个节点而不是一个节点。片断实例的挂载元素上的非流程控制指令被忽略。

replace

类型： Boolean

默认值： true

限制： 只能与 template 选项一起用

详细：

决定是否用模板替换挂载元素。如果设为 true（这是默认值），模板将覆盖挂载元素，并合并挂载元素和模板根节点的 attributes。如果设为 false 模板将覆盖挂载元素的内容，不会替换挂载元素自身。

示例：

```vue
<div id="replace" class="foo"></div>

new Vue({
 el: '#replace',
 template: '<p class="bar">replaced</p>'
})
//结果：
<p class="foo bar" id="replace">replaced</p>
```

replace 设为 false：
```vue
<div id="insert" class="foo"></div>
new Vue({
 el: '#insert',
 replace: false,
 template: '<p class="bar">inserted</p>'
})
//结果：
<div id="insert" class="foo">
 <p class="bar">inserted</p>
</div>
```
