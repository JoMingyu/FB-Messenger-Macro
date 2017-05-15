import fbchat

client = None


def login(id, password):
    try:
        client = fbchat.Client(id, password)
        return client
    except Exception as e:
        return False


def login_by_session(session_file_name):
    client = fbchat.Client(None, None, do_login=False)
    client.loadSession(session_file_name)
    return client
