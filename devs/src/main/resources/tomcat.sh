# tomcat自启动脚本
#!/bin/sh
# chkconfig: 345 99 10
# description: Auto-starts tomcat
# /etc/init.d/tomcatd
# Tomcat auto-start
# Source function library.
#. /etc/init.d/functions
# source networking configuration.
#. /etc/sysconfig/network
RETVAL=0
# tomcat自启动脚本
#!/bin/sh
# chkconfig: 345 99 10
# description: Auto-starts tomcat
# /etc/init.d/tomcatd
# Tomcat auto-start
# Source function library.
#. /etc/init.d/functions
# source networking configuration.
#. /etc/sysconfig/network
RETVAL=0
export JDK_HOME=/usr/java/jdk1.8.0_101
export CATALINA_HOME=/home/dev/apache-tomcat-8.5.5
export CATALINA_BASE=/home/dev/apache-tomcat-8.5.5
start()
{
        if [ -f $CATALINA_HOME/bin/startup.sh ];
          then
            echo $"Starting Tomcat"
                $CATALINA_HOME/bin/startup.sh
            RETVAL=$?
            echo " OK"
            return $RETVAL
        fi
}
stop()
{
        if [ -f $CATALINA_HOME/bin/shutdown.sh ];
          then
            echo $"Stopping Tomcat"
                $CATALINA_HOME/bin/shutdown.sh
            RETVAL=$?
            sleep 1
            ps -fwwu tomcat | grep apache-tomcat|grep -v grep | grep -v PID | awk '{print $2}'|xargs kill -9
            echo " OK"
            # [ $RETVAL -eq 0 ] && rm -f /var/lock/...
            return $RETVAL
        fi
}
case "$1" in
 start)
        start
        ;;
 stop)
        stop
        ;;
 restart)
         echo $"Restaring Tomcat"
         $0 stop
         sleep 1
         $0 start
         ;;
 *)
        echo $"Usage: $0 {start|stop|restart}"
        exit 1
        ;;
esac
exit $RETVAL
export CATALINA_HOME=/home/dev/apache-tomcat-8.5.5
export CATALINA_BASE=/home/dev/apache-tomcat-8.5.5
start()
{
        if [ -f $CATALINA_HOME/bin/startup.sh ];
          then
            echo $"Starting Tomcat"
                $CATALINA_HOME/bin/startup.sh
            RETVAL=$?
            echo " OK"
            return $RETVAL
        fi
}
stop()
{
        if [ -f $CATALINA_HOME/bin/shutdown.sh ];
          then
            echo $"Stopping Tomcat"
                $CATALINA_HOME/bin/shutdown.sh
            RETVAL=$?
            sleep 1
            ps -fwwu tomcat | grep apache-tomcat|grep -v grep | grep -v PID | awk '{print $2}'|xargs kill -9
            echo " OK"
            # [ $RETVAL -eq 0 ] && rm -f /var/lock/...
            return $RETVAL
        fi
}
case "$1" in
 start)
        start
        ;;
 stop)
        stop
        ;;
 restart)
         echo $"Restaring Tomcat"
         $0 stop
         sleep 1
         $0 start
         ;;
 *)
        echo $"Usage: $0 {start|stop|restart}"
        exit 1
        ;;
esac
exit $RETVAL