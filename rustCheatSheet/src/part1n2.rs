fn main() {
    println!("Hello, world!");
    printing::announceTime();
    printing::sayGoodbye();
}

mod printing {
    pub mod timeStuff;
    pub use timeStuff::giveUsTheTime as announceTime;
    pub fn sayGoodbye() {
        println!("Goodbye!")
    }
}
