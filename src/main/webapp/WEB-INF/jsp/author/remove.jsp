<h2>Czy chcesz usunac autora ${author.fullName} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${author.id}">
    <button type="submit" value="yes" name="confirmed">TAK</button>
    <button type="submit" value="no" name="confirmed">NIE</button>
</form>