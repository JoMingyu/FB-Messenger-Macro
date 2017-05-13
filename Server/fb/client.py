import fbchat

client = None


def login(id, password):
    try:
        client = fbchat.Client(id, password, debug=False)
        return True
    except Exception as e:
        return False
