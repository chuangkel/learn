# 执行命令将db.php加入不提交队列

git update-index --assume-unchanged include/db.php

# 执行命令将db.php取消加入不提交队列

git update-index --no-assume-unchanged include/db.php

# 说明：include/db.php 为文件的路径

git rm -r 移除出版本控制
git rm -rf 强行删除

git stash //暂存你当前不想提交的修改，回到开始

在项目中加入.gitignore文件  /A/*  表示排除A文件目录下的所以文件



1.修改命令

git remote origin set-url [url]

2.先删后加

git remote rm origin
git remote add origin [url]

git remote add origin https://github.com/chuangkel/delgen.git

## git stash 用法

* git stash: 备份当前的工作区的内容，从最近的一次提交中读取相关内容，让工作区保证和上次提交的内容一致。同时，将当前的工作区内容保存到Git栈中。
* git stash pop: 从Git栈中读取最近一次保存的内容，恢复工作区的相关内容。由于可能存在多个Stash的内容，所以用栈来管理，pop会从最近的一个stash中读取内容并恢复。
* git stash list: 显示Git栈内的所有备份，可以利用这个列表来决定从那个地方恢复。
* git stash clear: 清空Git栈。此时使用gitg等图形化工具会发现，原来stash的哪些节点都消失了。

关于Git Stash的详细解释，适用场合，这里做一个说明：
>使用git的时候，我们往往使用branch解决任务切换问题，例如，我们往往会建一个自己的分支去修改和调试代码, 如果别人或者自己发现原有的分支上有个不得不修改的bug，我们往往会把完成一半的代码 commit提交到本地仓库，然后切换分支去修改bug，改好之后再切换回来。这样的话往往log上会有大量不必要的记录。其实如果我们不想提交完成一半或者不完善的代码，但是却不得不去修改一个紧急Bug，那么使用'git stash'就可以将你当前未提交到本地（和服务器）的代码推入到Git的栈中，这时候你的工作区间和上一次提交的内容是完全一样的，所以你可以放心的修 Bug，等到修完Bug，提交到服务器上后，再使用'git stash apply'将以前一半的工作应用回来。也许有的人会说，那我可不可以多次将未提交的代码压入到栈中？答案是可以的。当你多次使用'git stash'命令后，你的栈里将充满了未提交的代码，这时候你会对将哪个版本应用回来有些困惑，'git stash list'命令可以将当前的Git栈信息打印出来，你只需要将找到对应的版本号，例如使用'git stash apply stash@{1}'就可以将你指定版本号为stash@{1}的工作取出来，当你将所有的栈都应用回来的时候，可以使用'git stash clear'来将栈清空。
在这里顺便提下git format-patch -n , n是具体某个数字， 例如 'git format-patch -1' 这时便会根据log生成一个对应的补丁，如果 'git format-patch -2' 那么便会生成2个补丁，当然前提是你的log上有至少有两个记录