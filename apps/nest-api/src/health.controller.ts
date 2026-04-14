import { Controller, Get } from '@nestjs/common';

@Controller()
export class HealthController {
  @Get('health')
  async health() {
    const springUrl = process.env.SPRING_API_URL || 'http://localhost:8080';

    let springStatus = 'unreachable';
    try {
      const response = await fetch(`${springUrl}/health`);
      const data = await response.json();
      springStatus = data.status;
    } catch {
      springStatus = 'unreachable';
    }

    return {
      nest: 'ok',
      spring: springStatus,
    };
  }
}
