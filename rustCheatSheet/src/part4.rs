#[test]
fn main() {
    println!("Hello, world!");
    let x = 17;
    let y = 9;
    let _ = findAnswer();
    let answer = findAnsweri2(x, y);
    println!("The answer is: {}", answer);
}

fn findAnswer() {
    let answer = 42;
    println!("The answer is {}", answer);
}

fn findAnsweri2(x: i32, y: i32) -> i32 {
    x + y + 5
}

#[cfg(test)]
mod tests {
    use crate::part4::findAnsweri2;

    fn testFindAnswer() {
        let x = 5;
        let y = 7;
        let answer = findAnsweri2(x, y);
        assert_eq!(17, answer);
    }
}
