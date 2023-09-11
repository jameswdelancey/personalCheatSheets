#[allow(unused_imports)]
use Model::Hands;

mod Model {
    pub struct Item {
        what: String,
        present: bool,
    }

    pub struct Hands {
        left: Item,
        right: Item,
    }
    impl Hands {
        pub fn new() -> Hands {
            Hands {
                left: Item {
                    what: "an apple".to_owned(),
                    present: true,
                },
                right: Item {
                    what: "a banana".to_owned(),
                    present: true,
                },
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
            Item::reportItem(&self.left, "Left");
            Item::reportItem(&self.right, "Right");
        }
    }

    impl Item {
        fn reportItem(&self, which: &str) {
            if self.present {
                println!("{} hand is holding {}", which, self.what);
            } else {
                println!("{} hand is not holding anything", which);
            }
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
