#!/usr/bin/python
from subprocess import call
import xml.etree.ElementTree as etree
import re

root = etree.parse("pom.xml")
version = root.find('./{http://maven.apache.org/POM/4.0.0}version').text
verGroups = re.match('(\d+)\.(\d+)\.?(\d+)?-SNAPSHOT', version)
currMajor = int(verGroups.group(1))
currMinor = int(verGroups.group(2))
releaseVersion = None
nextVersion = None

if verGroups.group(3):
    currPatch = int(verGroups.group(3))
    nextPatch = currPatch + 1
    releaseVersion = "{0}.{1}.{2}".format(currMajor, currMinor, currPatch)
    nextVersion = "{0}.{1}.{2}-SNAPSHOT".format(currMajor, currMinor, nextPatch)
else:    
    nextMinor = currMinor + 1
    releaseVersion = "{0}.{1}".format(currMajor, currMinor)
    nextVersion = "{0}.{1}-SNAPSHOT".format(currMajor, nextMinor)
    
call(["./release.sh", releaseVersion, nextVersion])
    
