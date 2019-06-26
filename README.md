# pweb3j
Pchain java API

This repository is evloved from web3j https://github.com/web3j/web3j, with changing package name from 'org.web3j' to 'org.pweb3j', and rewrite related classes/functions to adapt to pchain.

All pchain specific RPC APIs are listed in https://github.com/pchain-org/pchain/wiki/JSON-RPC

# sending requests

For pchain is multi-chain structure, so the request should be sent to specific chain. And it just needs to add chain name after the http://host:port/. The following examples are using main chain - "pchain".

To send synchronous requests:

    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:6969/pchain
    Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();

To send asynchronous requests using a CompletableFuture (Future on Android):

    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:6969/pchain
    Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();

To use an RxJava Flowable:

    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:6969/pchain
    web3.web3ClientVersion().flowable().subscribe(x -> {
        String clientVersion = x.getWeb3ClientVersion();
        ...
    });

# more

All tests/examples can be found in https://github.com/pchain-org/pweb3j/tree/master/integration-tests

More guides and instructions could refer to https://github.com/web3j/web3j
