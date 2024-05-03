# The script used to validate the "GIT RELEASE TAG".
# "TAG" should be set as environment variable.
SEMVER_REGEX="^v(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)(\\-[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?(\\+[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?$"
if [[ $TAG =~ $SEMVER_REGEX ]]; then
    echo "Tag is Valid"
else
    echo "Invalid Tag: Tag should be of format v1.1.1 or v1.1.1-alpha.1 (Refer - https://semver.org/)"
    exit 2
fi