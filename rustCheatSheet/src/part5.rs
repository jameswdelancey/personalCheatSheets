#[test]
fn main() {
    let n = 42;
    println!("F({}) = {}", n, fib(n));
}

#[test]
fn main2() {
    let n = 42;
    println!("F({}) = {}", n, fib2(n));
}

fn fib(n: u32) -> u32 {
    if n == 0 {
        0
    } else if n == 1 {
        1
    } else {
        fib(n - 1) + fib(n - 2)
    }
}

fn fib2(n: u32) -> u32 {
    if n == 0 {
        0
    } else if n == 1 {
        1
    } else {
        let mut x = 1;
        let mut a = 0;
        let mut b = 1;
        loop {
            let next = a + b;
            a = b;
            b = next;
            x += 1;
            if x < n {
                continue;
            }
            break;
        }
        b
    }
}
