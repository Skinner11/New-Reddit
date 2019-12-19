
from datetime import date

end_me = """We're talking away
I don't know what
I'm to say I'll say it anyway
Today's another day to find you
Shying away
I'll be coming for your love, okay?
Take on me (take on me)
Take me on (take on me)
I'll be gone
In a day or two
So needless to say
I'm odds and ends
But I'll be stumbling away
Slowly learning that life is okay
Say after me
It's no better to be safe than sorry
Take on me (take on me)
Take me on (take on me)
I'll be gone
In a day or two""".split()

def get_template(id, date, subredit_id, title):
    return f"INSERT INTO REDDIT_POST  (POST_ID, CONTENT, CREATED_AT, TITLE, PIC, VOTES, PARENT_ID, USER_ID) VALUES ({id}, 'quality content{id}', '{date}', '{title}', '/images/{id}.png',  2, {subredit_id}, 1);\n"


with open('temp.txt', "w") as file:
    today = date.today().strftime("%Y-%m-%d")
    for i in range(0, 154):
        title = end_me[i%len(end_me)].replace("'", "''")
        file.write(get_template(i, today, (i % 2)+1, title))
    file.close()



