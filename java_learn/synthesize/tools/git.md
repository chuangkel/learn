# 执行命令将db.php加入不提交队列

git update-index --assume-unchanged include/db.php

# 执行命令将db.php取消加入不提交队列

git update-index --no-assume-unchanged include/db.php

# 说明：include/db.php 为文件的路径

git rm -r 移除出版本控制
git rm -rf 强行删除

git stash //暂存你当前不想提交的修改，回到开始

在项目中加入.gitignore文件  /A/*  表示排除A文件目录下的所以文件