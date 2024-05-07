# The script used to publish Pigeon Jar to GitHub Package Registry.
# "GITHUB_ACTOR", "GH_PERSONAL_ACCESS_TOKEN", "TAG" - should be set as environment variable.

cd flutter_module
cd android_integration
cd publish_pigeon

VERSION="${TAG#v}" # Remove prefix 'v' from TAG

export FLUTTER_HOME=$FLUTTER_ROOT

./gradlew \
  -Pmaven-url=https://maven.pkg.github.com/apvasanth03/Flutter-AndroidIntegration \
  -Pmaven-user=$GITHUB_ACTOR \
  -Pmaven-pwd=$GH_PERSONAL_ACCESS_TOKEN \
  -PbuildNumber=$VERSION \
  :app:publish