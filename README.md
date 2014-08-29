## Synopsis

The Scala asynchronous client for MusicBrainz, based on **Play Framework WS** and **Guice**. It uses, not yet stable,  [https://wiki.musicbrainz.org/Development/JSON_Web_Service]()

## Code Example

```scala
class MatchController @Inject() (musicbrainz: ArtistService) extends Controller {
 
def search(phrase: String) = Action.async {
  def searchQuery = Builder(value).build
           
  musicbrainz.search(searchQuery).map(_.toList).map(
    Some(_)
      .filter(_.nonEmpty)
      .map(toJson(_))
      .map(Ok(_))
    .getOrElse(NoContent)))
      
  def get(id: String) = Action.async {
    ArtistId.unapply(id)
      .map(musicbrainz.get(_)
        .map(_.map(toJson(_))
          .map(Ok(_))
        .getOrElse(NotFound)))
      .getOrElse(successful(BadRequest))
    .get(_)
      .map(_.map(toJson(_))
        .map(Ok(_))
        .getOrElse(NotFound)))
      .getOrElse(successful(BadRequest))
  }
}
```

## Motivation

I have started this project to provide lightweight and asynchronous integration with Musicbrainz in one of the Scala projects based on Play.

## Installation

Current `master` is compatibile with the Play 2.3.3. To publish into local Ivy repository run:

    git clone https://github.com/abankowski/musicbrainz-scala-client.git
    sbt publish-local

## API Reference

TODO

## Tests

TODO

## Contributors

Pull requests welcomed.

## License

The MIT License (MIT)

Copyright (c) 2014 Artur Bańkowski