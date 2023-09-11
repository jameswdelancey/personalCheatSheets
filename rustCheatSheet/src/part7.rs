use Model::Hands;

mod Model {
    use std::fmt::Display;
    // pub trait Displayable {
    //     fn display(&self) -> String;
    // }
    pub enum Fruit {
        Apple,
        Banana,
    }
    // impl Displayable for Fruit {
    //     // fn display(&self) -> String {
    //     //     if let Fruit::Apple = self {
    //     //         "an apple".to_owned()
    //     //     } else {
    //     //         "a banana".to_owned()
    //     //     }
    //     // }
    //     fn display(&self) -> String {
    //         match self {
    //             Fruit::Apple => "an apple".to_owned(),
    //             Fruit::Banana => "a banana".to_owned(),
    //         }
    //     }
    // }
    // pub enum Item<T> {
    //     Something(T),
    //     Nothing,
    // }
    impl Display for Fruit {
        fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
            match self {
                Fruit::Apple => f.write_str("an apple"),
                Fruit::Banana => f.write_str("a banana"),
            }
        }
    }

    pub struct Hands {
        left: Option<Fruit>,
        right: Option<Fruit>,
    }
    impl Hands {
        pub fn new() -> Hands {
            Hands {
                left: Option::Some(Fruit::Apple),
                right: Option::Some(Fruit::Banana),
            }
        }

        pub fn juggle(mut self) -> Self {
            println!("Let's juggle!");
            let air = self.left;
            self.left = self.right;
            self.right = air;
            self
        }
        pub fn report(&self) {
            reportItem(&self.left, "Left");
            reportItem(&self.right, "Right");
        }
    }

    pub fn reportItem<T: Display>(item: &Option<T>, which: &str) {
        match item {
            Option::Some(what) => {
                println!("{} hand is holding {}", which, what)
            }
            _ => println!("{} hand is not holding anything", which),
        }
    }
}

#[test]
fn main() {
    let mut hands = Hands::new();
    hands.report();
    hands = hands.juggle();
    hands.report();
}
