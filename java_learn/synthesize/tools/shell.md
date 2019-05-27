[root@zhgl pms-service]# cat start.sh 
#!/bin/sh
cd pms-service
nohup java -jar pms-service.jar jfile=config/application.properties >/dev/null  &
echo $! > /var/run/pms-service.pid
[root@zhgl pms-service]# cat validateStart.sh 
#!/bin/bash
sleep 15s
port=`netstat -ano | grep -v grep | grep -w 12280`
if [ -n "$port" ]; then
        echo 0;
else
        echo "start fail!" 1>&2;
fi
[root@zhgl pms-service]# cat stop.sh 
#!/bin/sh 
PID=$(cat /var/run/pms-service.pid)
kill -9 $PID
[root@zhgl pms-service]#
