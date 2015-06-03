#!/bin/bash
set -o errexit
if [ "$#" != "2" ]; then
    echo "Must provide 2 arguments:"
    echo "  release: the name of release version to build"
    echo "  snapshot: the name of the next snapshot version"
    exit 1
fi
# Make sure master and release branches are up to date
git fetch origin
git fetch origin release:release
masterDiff=$(git log HEAD..origin/master --oneline)
if [[ "$masterDiff" != "" ]]; then
    echo "Branch 'master' is not up to date."
    exit 1
fi
releaseDiff=$(git log release..origin/release --oneline)
if [[ "$releaseDiff" != "" ]]; then
    echo "Branch 'release' is not up to date."
    exit 1
fi

parent_pom=pom.xml
# Create environment variables for release version and snapshot
export RELEASE="$1"
export SNAPSHOT="$2"
# Create a release candidate branch
git flow release start $RELEASE
# Update the maven poms for the release version
mvn -f $parent_pom -DnewVersion=$RELEASE versions:set
# Commit the pom version changes.
git commit -am "Updating version for $RELEASE"
# Finish the release
git flow release finish -m "$RELEASE" $RELEASE
# Update the master branch for the new snapshot version
mvn -f $parent_pom -DnewVersion=$SNAPSHOT versions:set
# Commit the pom version changes
git commit -am "Updating version for $SNAPSHOT"
# Push master, release, and tags
git push origin master
git push origin release
git push --tags
echo "All done!"

