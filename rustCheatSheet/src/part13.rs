use std::rc::Rc;

struct Node {
    value: &'static str,
    edges: Vec<Rc<Node>>,
}

impl Node {
    fn display(&self) {
        println!("Value: {}", self.value);
        for edge in &self.edges {
            edge.display();
        }
        println!("UP");
    }
}
impl Drop for Node {
    fn drop(&mut self) {
        println!("Dropping {}", self.value);
    }
}

#[test]
fn main3() {
    let e = Rc::new(Node {
        value: "E",
        edges: vec![],
    });
    let d = Rc::new(Node {
        value: "D",
        edges: vec![e.clone()],
    });
    let a = Node {
        value: "A",
        edges: vec![
            Rc::new(Node {
                value: "B",
                edges: vec![],
            }),
            Rc::new(Node {
                value: "C",
                edges: vec![d.clone(), e.clone()],
            }),
            d.clone(),
            e,
        ],
    };
    let dWeak = Rc::downgrade(&d);
    a.display();
    drop(a);
    drop(d);
    if let Some(dStrong) = dWeak.upgrade() {
        println!("D is still alive: {}", dStrong.value);
        dStrong.display();
    } else {
        println!("D is dead");
    }
    println!("Done");
}

use std::cell::RefCell;
struct Cat2 {
    timesSpoken: RefCell<usize>,
}
impl Cat2 {
    fn report(&self) {
        let timesSpoken = self.timesSpoken.borrow();
        println!("Spoken {} times", timesSpoken);
    }
}
trait Animal {
    fn speak(&self);
}
impl Animal for Cat2 {
    fn speak(&self) {
        let mut timesSpoken = self.timesSpoken.borrow_mut();
        *timesSpoken += 1;
        println!("Meow");
    }
}
#[test]
fn main5() {
    let cat = Cat2 {
        timesSpoken: RefCell::new(0),
    };
    cat.speak();
    cat.speak();
    cat.speak();
    cat.report();
}
