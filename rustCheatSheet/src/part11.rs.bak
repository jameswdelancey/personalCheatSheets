use std::io::{BufRead, Write};

#[test]
fn main() {
    let path = std::env::var("PATH").unwrap();
    println!("PATH: {}", path);
    let folders = std::env::split_paths(&path);
    for folder in folders {
        println!("Folder in PATH: {:?}", folder);
    }
}

#[test]
fn main2() {
    let args = std::env::args();
    for arg in args {
        println!("Arg: {}", arg);
    }
}
#[test]
fn main3() {
    let args = std::env::args();
    for arg in args {
        std::io::stdout()
            .write_all(format!("Arg: {}\n", arg).as_bytes())
            .unwrap();
    }
}
#[test]
fn main4() {
    let mut input = String::new();
    let n = std::io::stdin().read_line(&mut input).unwrap();
    println!("You typed: {} ({} bytes)", input, n);
}

#[test]
fn main5() {
    let file = std::fs::File::open("src/part11.rs").unwrap();
    let mut newFile = std::fs::File::create("src/part11.rs.bak").unwrap();
    let reader = std::io::BufReader::new(file);
    for line in reader.lines() {
        let line = line.unwrap();
        // writeln! also can work
        newFile.write_all(line.as_bytes()).unwrap();
        newFile.write_all(b"\n").unwrap();
    }
}

#[test]
fn main6() {
    let receiver = std::net::UdpSocket::bind("127.0.0.1:0").unwrap();
    println!(
        "We bound port {} for receiving",
        receiver.local_addr().unwrap().port()
    );
    let sender = std::net::UdpSocket::bind("127.0.0.1:0").unwrap();
    sender
        .send_to(b"Hello, World!", receiver.local_addr().unwrap())
        .unwrap();
}

#[derive(Debug)]
enum Error {
    Open(std::io::Error),
    Read(std::io::Error),
}

#[test]
fn main7() -> Result<(), Error> {
    // let file = match std::fs::File::open("src/part11.rs") {
    //     Ok(file) => file,
    //     Err(err) => return Err(Error::Open(err)),
    // };
    let file = std::fs::File::open("src/part11.rs").map_err(Error::Open)?;
    let reader = std::io::BufReader::new(file);
    for line in reader.lines() {
        // let line = match line.map_err(|err| Error::Read(err)) {
        //     Ok(line) => line,
        //     Err(err) => return Err(err),
        // };
        let line = line.map_err(|err| Error::Read(err))?;
        println!("{}", line);
    }
    Ok(())
}
