import random

class Player:
    def __init__(self, name):
        self.name = name
        self.score = 0

    def spin(self):
        input(f"{self.name}, press enter to spin the wheel...")
        self.score = random.randint(1, 20) * 5
        print(f"{self.name} spun {self.score}.\n")

def play_game():
    players = [Player("Player 1"), Player("Player 2"), Player("Player 3")]
    winner = None

    while winner is None:
        for player in players:
            player.spin()

        max_score = max(player.score for player in players)
        winners = [player for player in players if player.score == max_score]

        if len(winners) == 1:
            winner = winners[0]
        else:
            print("Tie! Players spin again.")
            input("Press enter to continue...")
            print()

    print(f"{winner.name} wins with a score of {winner.score}!")

if __name__ == "__main__":
    play_game()