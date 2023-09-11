use std::alloc::{alloc, Layout};
fn allocateNumbers() -> Box<SomeHugeType> {
    let layout = Layout::new::<SomeHugeType>();
    let ptr = unsafe { alloc(layout) } as *mut SomeHugeType;
    unsafe { Box::from_raw(ptr) }
}
fn generateNumbersOnHeap() -> Box<SomeHugeType> {
    let mut numbers = allocateNumbers();
    for (i, n) in numbers.iter_mut().enumerate() {
        *n = (i * 2) as i32;
    }
    numbers
}
const ANSWER: i32 = 42;
static mut QUESTION: String = String::new();

type SomeHugeType = [i32; 10_000_000];

fn generateNumbers() -> SomeHugeType {
    let mut numbers = [0; 10_000_000];
    for (i, n) in numbers.iter_mut().enumerate() {
        *n = (i * 2) as i32;
    }
    numbers
}

#[test]
fn main() {
    generateNumbers();
}

#[test]
fn main2() {
    generateNumbersOnHeap();
}

struct Node {
    value: i32,
    left: Option<Box<Node>>,
    right: Option<Box<Node>>,
}

impl Node {
    fn display(&self) {
        println!("{}", self.value);
        if let Some(ref left) = self.left {
            left.display();
        }
        if let Some(ref right) = self.right {
            right.display();
        }
        println!("UP");
    }
}

#[test]
fn main3() {
    let root = Node {
        value: 2,
        left: Some(Box::new(Node {
            value: 1,
            left: None,
            right: None,
        })),
        right: Some(Box::new(Node {
            value: 3,
            left: None,
            right: None,
        })),
    };
    root.display();
}

struct Cat {}
struct Dog {}
trait Animal {
    fn speak(&self);
}
impl Animal for Cat {
    fn speak(&self) {
        println!("Meow!");
    }
}
impl Animal for Dog {
    fn speak(&self) {
        println!("Woof!");
    }
}
fn workWithAnimals(animals: &[Box<dyn Animal>]) {
    for animal in animals {
        animal.speak();
    }
}
#[test]
fn main4() {
    let cat = Cat {};
    let dog = Dog {};
    let mut animals: Vec<Box<dyn Animal>> = Vec::new();
    animals.push(Box::new(cat));
    animals.push(Box::new(dog));
    workWithAnimals(&animals);
}
