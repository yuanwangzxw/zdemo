#! /bin/bash
imageName=192.168.56.101:80/test/authentication:0.0.1-SNAPSHOT
contanierName=authentication

containerId=$(docker ps -a |grep -w ${contanierName}|awk 'print $1')
if [ -n "$containerId" ]; then
    docker stop ${containerId}
    docker rm ${containerId}
    echo "成功删除容器:$containerId"
fi
imageId=$(docker images|grep -w ${imageName} | awk 'print $3')
if [ "$imageId" != "" ]; then
    docker rmi -f ${imageId}
fi
docker login -u admin -p Harbor 192.168.56.101:80
docker pull ${imageName}

docker run -di -p 6001:6001 ${contanierName}