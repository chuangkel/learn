rpm -aq | grep mysql # 查询mysql是否安装

wget https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz

tar xzvf mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
rm -rf  mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz

mv mysql-5.7.24-linux-glibc2.12-x86_64 /usr/local/
cd /usr/local/
mv mysql-5.7.24-linux-glibc2.12-x86_64 mysql
mkdir /usr/local/mysql/data

chown -R mysql:mysql /usr/local/mysql
chmod -R 755 /usr/local/mysql
#安装所需链接库文件
sudo yum install  libaio-devel.x86_64
sudo yum -y install numactl

cd /usr/local/mysql/bin
./mysqld --initialize --user=mysql --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql


