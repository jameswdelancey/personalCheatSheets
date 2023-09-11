use std::collections::{HashMap, VecDeque};

#[test]
fn main() {
    let mut primes = Vec::new();
    primes.push(2);
    primes.push(3);
    primes.push(5);
    println!("{:?}", primes);
}
#[test]
fn main2() {
    let mut primes = vec![2, 3, 5];
    println!("{:?}", primes);
    primes.remove(1);
    println!("{:?}", primes);
}
#[test]
fn main3() {
    let primes = vec![2, 3, 5];

    let mut i = 0;
    while i < primes.len() {
        println!("{}", primes[i]);
        i += 1;
    }
    let mut primes_iter = primes.iter();
    while let Some(prime) = primes_iter.next() {
        println!("{}", prime);
    }
}
#[test]
fn main4() {
    let primes = vec![2, 3, 5];
    for prime in &primes {
        println!("{}", prime);
    }
    for (i, prime) in primes.iter().enumerate() {
        println!("{}: {}", i, prime);
    }
}
#[test]
fn main5() {
    let mut primes = VecDeque::new();
    primes.push_back(2);
    primes.push_back(3);
    primes.push_back(5);
    primes.push_front(1);

    for prime in &primes {
        println!("{}", prime);
    }
}
#[test]
fn main6() {
    // or BTreeMap
    let mut grid = HashMap::new();
    grid.insert((2, 3), "tree");
    grid.insert((4, 7), "rock");
    *grid.entry((-3, 1)).or_insert("empty") = "bird";
    grid.remove(&(4, 7));

    let coords = (2, 2);
    match grid.get(&coords) {
        Some(thing) => println!("There's a {} at {:?}", thing, coords),
        None => println!("There's nothing at {:?}", coords),
    }
}
// HashSet
// BinaryHeap
