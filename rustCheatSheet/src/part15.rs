#[test]
fn main() {
    // for async stuff, try to use stuff owned by the async block,
    // or main function, or static lifetimes.
    const NUM_MESSAGES: usize = 1000;
    // let receiver = std::net::UdpSocket::bind("127.0.0.1:0").unwrap();
    // let sender = std::net::UdpSocket::bind("127.0.0.1:0").unwrap();
    let receiver = futures::executor::block_on(async {
        async_std::net::UdpSocket::bind("127.0.0.1:0")
            .await
            .unwrap()
    });
    let sender = futures::executor::block_on(async {
        async_std::net::UdpSocket::bind("127.0.0.1:0")
            .await
            .unwrap()
    });
    let senderFuture = async {
        for _ in 0..NUM_MESSAGES {
            sender
                .send_to(b"Hello, World!", receiver.local_addr().unwrap())
                .await
                .unwrap();
            futures_timer::Delay::new(std::time::Duration::from_millis(1)).await;
        }
    };
    let receiverFuture = async {
        let mut buffer = [0; 256];
        let mut count = 0;
        for _ in 0..NUM_MESSAGES {
            let _ = receiver.recv_from(&mut buffer).await.unwrap();
            count += 1;
            println!("Received message {}", count);
        }
    };
    futures::executor::block_on(async {
        futures::join!(senderFuture, receiverFuture);
    });
}
