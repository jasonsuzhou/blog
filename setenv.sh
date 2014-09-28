#!/bin/bash
#read configure file
CFG_JAVA_HOME=`cat ./release.cfg | grep JAVA_HOME | awk -F= '{print $2}'`
CFG_MAVEN_HOME=`cat ./release.cfg | grep MAVEN_HOME | awk -F= '{print $2}'`
#setup environment
export JAVA_HOME=$CFG_JAVA_HOME
export MAVEN_HOME=$CFG_MAVEN_HOME
export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin
echo $PATH