import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/pigeon/generated/search.g.dart',
  dartOptions: DartOptions(),
  dartPackageName: 'sample_pigeon',
  kotlinOut: 'pigeon/generated/android/src/Search.g.kt',
  kotlinOptions: KotlinOptions(
    package: 'io.github.apvasanth03.flutter_module.pigeon',
  ),
))
class SearchRequest {
  final String query;

  SearchRequest({required this.query});
}

class SearchReply {
  final String result;

  SearchReply({required this.result});
}

@HostApi()
abstract class Api {
  @async
  SearchReply search(SearchRequest request);
}
