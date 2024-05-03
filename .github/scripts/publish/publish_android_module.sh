# The script used to publish Android Module to GitHub Package Registry.
# "GITHUB_ACTOR", "GH_PERSONAL_ACCESS_TOKEN", "TAG" - should be set as environment variable.
cd flutter_module

flutter pub get

cd .android

VERSION="${TAG#v}" # Remove prefix 'v' from TAG

./gradlew \
  -I=../android_integration/publish_module/aar_init_script.gradle \
  -Pmaven-url=https://maven.pkg.github.com/apvasanth03/Flutter-AndroidIntegration \
  -Pmaven-user=$GITHUB_ACTOR \
  -Pmaven-pwd=$GH_PERSONAL_ACCESS_TOKEN \
  -PbuildNumber=$VERSION \
  assembleAarRelease

