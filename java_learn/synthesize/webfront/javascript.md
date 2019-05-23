

## javascript

```
1、只要“&&”前面是false，无论“&&”后面是true还是false，结果都将返“&&”前面的值;

2、只要“&&”前面是true，无论“&&”后面是true还是false，结果都将返“&&”后面的值;

1、只要“||”前面为false,不管“||”后面是true还是false，都返回“||”后面的值。

2、只要“||”前面为true,不管“||”后面是true还是false，都返回“||”前面的值。
在遍历DOM时，通常用$(selector).each(function(index,element))函数；
```


在遍历数据时，通常用$.each(dataresource,function(index,element))函数。
```
$.each(arr,function(key,value){
   //遍历arr数组 
});
```

iframeId是否在processTabs数组里面，不在返回-1.
$.inArray() 函数用于在数组中查找指定值，并返回它的索引值（如果没有找到，则返回-1）

提示：源数组不会受到影响，过滤结果只反映在返回的结果数组中。
```
$.inArray(iframeId, processTabs);
```
index	必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
howmany	必需。要删除的项目数量。如果设置为 0，则不会删除项目。
```
.splice
arrayObject.splice(index,howmany,item1,.....,itemX)
```
当 DOM（文档对象模型） 已经加载，并且页面（包括图像）已经完全呈现时，会发生 ready 事件。
由于该事件在文档就绪后发生，因此把所有其他的 jQuery 事件和函数置于该事件中是非常好的做法
```
$(function)
$().ready(function)
$(document).ready(function)
```

比较两个字符串是否相等 == 
将对象转换成字符串
```
JSON.stringify
```
setTimeout()和setInterval()这两个函数完成，它们内部运行机制完全一样，区别在于前者指定的代码是一次性执行的，后者是反复执行。
```
// 设置定时刷新:
    setInterval(function(){
    	refreshProcessState();//状态刷新
    }, 2000);
```