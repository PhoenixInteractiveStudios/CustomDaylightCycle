[version-shield]: https://img.shields.io/github/v/release/PhoenixInteractiveStudios/CustomDaylightCycle?include_prereleases
[license-shield]: https://img.shields.io/github/license/PhoenixInteractiveStudios/CustomDaylightCycle
[build-shield]: https://img.shields.io/github/actions/workflow/status/PhoenixInteractiveStudios/CustomDaylightCycle/build.yaml

<!--suppress HtmlRequiredAltAttribute, CheckImageSize -->
<img align="right" src=".github/customdaylightcycle.png" height="200" width="200">

[![version-shield]](https://github.com/PhoenixInteractiveStudios/CustomDaylightCycle/releases)
[![license-shield]](LICENSE)
[![build-shield]](https://github.com/PhoenixInteractiveStudios/CustomDaylightCycle/actions/workflows/build.yaml)

# CustomDaylightCycle
This simple spigot plugin allows you to customize how long each day / night should be for each world.

### Configuration
The factor for the time each day / night should be sped up or slowed down by can be configured via the `config.yml` file
in the plugin directory. A default config with all existing worlds will be created on first launch.

```yaml
worlds:
  world:
    day: 0.5
    night: 2.0
```

This example configuration would modify the daylight cycle in the default world so that each day would last twice as
long and each night would pass in half the time it would normally take.