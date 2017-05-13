import fbchat

client = None


def login(id, password):
    try:
        client = fbchat.Client(id, password)
        return True
    except Exception as e:
        return False
