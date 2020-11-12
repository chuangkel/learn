# ssh别名登陆
Host 70
	HostName 118.31.226.70
	Port 22
	User root
	IdentityFile ~/.ssh/id_rsa.pub

Host 178
	HostName 47.244.227.178 
	Port 22
	User root
	IdentityFile ~/.ssh/id_rsa_178.pub
# 将以上配置拷贝到~/.ssh/config 执行ssh 178
