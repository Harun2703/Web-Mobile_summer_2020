//computer choosing random number
//JavaScript const variables must be assigned a value when they are declared
function computer_choice() {
    const randomNum = Math.random();
    if (randomNum < 0.34) return "r";
    if (randomNum < 0.67 && randomNum > 0.34) return "p";
    else return "s";
}
//converting r, p, s into rock, paper and scissors
function convertToWord(letter) {
    if (letter === "r") return "Rock";
    if (letter === "s") return "Scissors";
    else return "Paper";
}

//three function used win, lose, and draw.
function win(userChoice, computerChoice) {
    document.querySelector(".results>p").innerHTML = " Your's choice: " + convertToWord(userChoice) + ". Computer's choice: " + convertToWord(computerChoice) + ". You Win! Let's try again.";

}

function lose(userChoice, computerChoice) {
    document.querySelector(".results>p").innerHTML = " Your's choice: " + convertToWord(userChoice) + ". Computer's choice: " + convertToWord(computerChoice) + ". You lose! Try Again.";
}

function draw(userChoice, computerChoice) {
    document.querySelector(".results>p").innerHTML = " Your's choice: " + convertToWord(userChoice) + ". Computer's choice: " + convertToWord(computerChoice) + ". It is a draw! Play Again.";
}


//defining the main function
function rule(userChoice) {
    const computerChoice = computer_choice();
    if (userChoice + computerChoice === "rs") {
        win(userChoice, computerChoice);
    } else if (userChoice + computerChoice === "pr") {
        win(userChoice, computerChoice);
    } else if (userChoice + computerChoice === "sp") {
        win(userChoice, computerChoice);
    } else if (userChoice + computerChoice === "rp") {
        lose(userChoice, computerChoice);
    } else if (userChoice + computerChoice === "ps") {
        lose(userChoice, computerChoice);
    } else if (userChoice + computerChoice === "sr") {
        lose(userChoice, computerChoice);
    } else {
        draw(userChoice, computerChoice);
    }
}
//getting user input
function main() {
    document.getElementById("rock").addEventListener("click", function () {
        rule("r");
    });
    document.getElementById("paper").addEventListener("click", function () {
        rule("p");
    });
    document.getElementById("scissors").addEventListener("click", function () {
        rule("s");
    })
}

main();