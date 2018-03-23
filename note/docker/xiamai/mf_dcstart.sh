#!/bin/bash

#容器网段
mainsubnet='172.13.0.'
#部署路径
maindir='/usr/local/mf/'
#war包打包路径
mainwardir='/usr/local/docker-app/mf-test/webapps/'

servicewarname='miaofen-service'
apiwarname='miaofen-api'



mkdir $maindir

logdir=$maindir'/weblogs/'
wardir=$maindir'/webapps/'

mkdir $logdir
mkdir $wardir


rm -rf $wardir$servicewarname
rm -rf $wardir$apiwarname

cp -r $mainwardir$servicewarname $wardir
cp -r $mainwardir$apiwarname $wardir


network='mf-net'
subnet=$mainsubnet'0/16'

zkname='mf-zk'
servicename='mf-service'
apiname='mf-api'

#端口配置
serviceport='9000'
apiport='8001'

#日志持久化路径
servicelogdir=$logdir'/servicelogs'
apilogdir=$logdir'/apilogs'



#替换项目zk地址配置

zkip=$mainsubnet'2'

serviceconfig=$wardir$servicewarname'/WEB-INF/classes/dubbo.properties'
apiconfig=$wardir$apiwarname'/WEB-INF/classes/dubbo.properties'

old_ip=$(cat $serviceconfig | grep dubbo.registry.address | awk -F"=" '{ print $2 }' | awk -F":" '{ print $1 }')

sed -i "s/$old_ip/$zkip/g" $serviceconfig
sed -i "s/$old_ip/$zkip/g" $apiconfig



/bin/bash dc_network.sh $network $subnet

/bin/bash dc_mfzk.sh $zkname $network $zkip

/bin/bash dc_mfservice.sh $servicename $network $mainsubnet'3' $serviceport $servicelogdir $wardir$servicewarname
sleep 2s
/bin/bash dc_mfapi.sh $apiname $network $mainsubnet'4' $apiport $apilogdir $wardir$apiwarname


