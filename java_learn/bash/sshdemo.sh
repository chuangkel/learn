ssh-keygen -t rsa -P '' -f ~/.ssh/$1
ssh-copy-id -i ~/.ssh/$1.pub $2@$3
