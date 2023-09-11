use std::{
    cell::RefCell,
    sync::{mpsc::channel, Arc, Mutex},
};

#[test]
fn main() {
    println!("Hello, world!");
    let steps = 10;
    let thread = std::thread::spawn(move || {
        for i in 0..steps {
            println!("Thread step: {}", i);
            std::thread::sleep(std::time::Duration::from_millis(100));
        }
        println!("Thread done");
        42
    });
    println!("Spawned thread");
    for i in 0..steps {
        println!("Main step: {}", i);
        std::thread::sleep(std::time::Duration::from_millis(100));
    }
    println!("Main done");
    let result = thread.join().unwrap();
    println!("Joined thread: {:?}", result);
    println!("Done");
}
#[test]
fn main2() {
    println!("Hello, world!");
    let steps = Arc::new(10);
    let thread = {
        let steps = steps.clone();
        std::thread::spawn(move || {
            for i in 1..=*steps {
                println!("Thread step: {}", i);
                std::thread::sleep(std::time::Duration::from_millis(100));
            }
            println!("Thread done");
            42
        })
    };
    println!("Spawned thread with a step count of {}!", steps);
    for i in 0..=*steps {
        println!("Main step: {}", i);
        std::thread::sleep(std::time::Duration::from_millis(100));
    }
    println!("Main done");
    let result = thread.join().unwrap();
    println!("Joined thread: {:?}", result);
    println!("Done");
}
#[test]
fn main3() {
    println!("Hello, world!");
    let steps = Arc::new(Mutex::new(10));
    let thread = {
        let steps = steps.clone();
        std::thread::spawn(move || {
            while *steps.lock().unwrap() > 0 {
                println!("Thread step: {}", steps.lock().unwrap());
                *steps.lock().unwrap() -= 1;
                std::thread::sleep(std::time::Duration::from_millis(100));
            }
            println!("Thread done");
            42
        })
    };
    println!(
        "Spawned thread with a step count of {}!",
        steps.lock().unwrap()
    );
    for i in 0..10 {
        println!("Main step: {}", i);
        std::thread::sleep(std::time::Duration::from_millis(100));
    }
    println!("Main done");
    let result = thread.join().unwrap();
    println!("Joined thread: {:?}", result);
    println!("Done");
}

#[test]
fn main4() {
    println!("Hello, world!");
    let (sender, receiver) = channel();
    let thread = std::thread::spawn(move || {
        let steps: i32 = receiver.recv().unwrap();
        for step in 1..=steps {
            println!("Thread step: {}", step);
            std::thread::sleep(std::time::Duration::from_millis(100));
        }
        println!("Thread done");
        42
    });
    sender.send(10).unwrap();
    println!("Spawned thread with a step count of {}!", 10);
    for i in 0..10 {
        println!("Main step: {}", i);
        std::thread::sleep(std::time::Duration::from_millis(100));
    }
    println!("Main done");
    let result = thread.join().unwrap();
    println!("Joined thread: {:?}", result);
    println!("Done");
}
