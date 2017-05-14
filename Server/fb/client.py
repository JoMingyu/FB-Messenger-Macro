import fbchat

client = None


def login(id, password):
    try:
        client = fbchat.Client(id, password)
        return client
    except Exception as e:
        return False
