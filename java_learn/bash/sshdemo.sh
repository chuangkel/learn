# 生成公钥/私钥位置  用户名 ip 
ssh-keygen -t rsa -P '' -f ~/.ssh/$1
ssh-copy-id -i ~/.ssh/$1.pub $2@$3
