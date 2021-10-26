<h2>Czy chcesz usunac ksiazke ${book.title} ?</h2>

<form method="post">
    <input type="hidden" name="toRemoveId" value="${book.id}">
    <button type="submit" value="yes" name="confirmed">TAK</button>
    <button type="submit" value="no" name="confirmed">NIE</button>
</form>