import 'package:flutter/material.dart';
import 'package:flutter_module/pigeon/generated/search.g.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Pigeon Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Pigeon Demo'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final Api _api = Api();
  String? _searchResult;

  @override
  void initState() {
    super.initState();

    _api.search(SearchRequest(query: 'Hello')).then((reply) {
      setState(() {
        _searchResult = reply.result;
      });
    }).catchError((error) {
      setState(() {
        _searchResult = 'Failed to search: $error';
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              _searchResult ?? 'Waiting for search result...',
            ),
            if (_searchResult == null) const CircularProgressIndicator(),
          ],
        ),
      ),
    );
  }
}
